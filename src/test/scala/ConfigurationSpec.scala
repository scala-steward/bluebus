import java.net.{URI, URL}

import bluebus.configuration.SBusConfig
import configuration.SBConfig
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.mockito.MockitoSugar
import org.scalatest.{BeforeAndAfterAll, FlatSpec, Matchers}


class ConfigurationSpec extends FlatSpec with Matchers with ScalaFutures with MockitoSugar with BeforeAndAfterAll {

  "when the SBusConfig is created, it" should
    "return the expected endpoint" in {

    val subject = SBusConfig(SBConfig.config)

    subject.endpoint shouldBe "http://127.0.0.1/test/messages"
  }

  "when the SBusConfig is called with an invalid queue name" should "fail the requirement" in {
    the [IllegalArgumentException] thrownBy {
      SBusConfig(rootUri=new URL("http://localhost"), queueName="", sasKeyName="", sasKey="")
    } should have message "requirement failed: Queue name must be specified"
  }

}
