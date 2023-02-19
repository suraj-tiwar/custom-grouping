import org.apache.storm.generated.GlobalStreamId
import org.apache.storm.grouping.CustomStreamGrouping
import org.apache.storm.shade.com.google.common.collect.ImmutableList
import org.apache.storm.task.WorkerTopologyContext

import java.util
import scala.collection.IterableOnce.iterableOnceExtensionMethods

class CustomLengthGrouping extends CustomStreamGrouping with Serializable{
  var tasks : Integer = _
  override def prepare(context: WorkerTopologyContext, stream: GlobalStreamId, targetTasks: util.List[Integer]): Unit = {
    tasks = targetTasks.size()
  }

  override def chooseTasks(taskId: Int, values: util.List[AnyRef]): util.List[Integer] = {
    val data : Integer = values.get(0).asInstanceOf[String].length
    ImmutableList.of(data % tasks)
  }
}
