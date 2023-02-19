import org.apache.storm.spout.SpoutOutputCollector
import org.apache.storm.task.TopologyContext
import org.apache.storm.topology.OutputFieldsDeclarer
import org.apache.storm.topology.base.BaseRichSpout
import org.apache.storm.tuple.{Fields, Values}

import java.util
import scala.util.Random

class wordSpout extends BaseRichSpout{
  var collector: SpoutOutputCollector = _
  var index : Integer = 0
  var context : TopologyContext = _
  var listOfWords: Seq[String] = Seq("one","twos","Threes","Foursss")
  override def open(conf: util.Map[String, AnyRef], context: TopologyContext, collector: SpoutOutputCollector): Unit = {
    this.collector = collector
    this.context = context
  }

  override def nextTuple(): Unit = {
    collector.emit(new Values(listOfWords(index)))
    index = (index + 1) % listOfWords.size
  }

  override def declareOutputFields(declarer: OutputFieldsDeclarer): Unit = {
    declarer.declare(new Fields("word"))
  }
}
