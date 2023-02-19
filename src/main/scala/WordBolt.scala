import org.apache.storm.StormSubmitter
import org.apache.storm.task.{OutputCollector, TopologyContext}
import org.apache.storm.topology.OutputFieldsDeclarer
import org.apache.storm.topology.base.BaseRichBolt
import org.apache.storm.tuple.{Fields, Tuple}

import java.util

class WordBolt extends BaseRichBolt{
  var collector : OutputCollector = _
  override def declareOutputFields(declarer: OutputFieldsDeclarer): Unit = {
    declarer.declare(new Fields("bolt-msg"))
  }

  override def prepare(topoConf: util.Map[String, AnyRef], context: TopologyContext, collector: OutputCollector): Unit = {
    this.collector = collector
  }

  override def execute(input: Tuple): Unit = {
    val res = input.getString(0)
    println(res)
    collector.ack(input)
  }
}
