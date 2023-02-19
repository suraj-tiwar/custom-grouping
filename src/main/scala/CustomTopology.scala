import org.apache.storm.{Config, StormSubmitter}
import org.apache.storm.topology.TopologyBuilder

class CustomTopology {
  val builder : TopologyBuilder = new TopologyBuilder()
  builder.setSpout("words",new wordSpout(),2)
  builder.setBolt("res",new WordBolt(),5).customGrouping("words",new CustomLengthGrouping())
  try
    {
      StormSubmitter.submitTopology("CustomGrouping",new Config(),builder.createTopology())
    }
  catch {
    case  e => e.printStackTrace
  }
}

object CustomTopology extends App{
  new CustomTopology()
}
