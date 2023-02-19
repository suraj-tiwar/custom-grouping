import org.apache.storm.topology.TopologyBuilder

class KafkaIntegration {
  val builder = new TopologyBuilder()
  builder.setSpout("spout",new wordSpout(),5)
}
