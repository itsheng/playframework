/*
 * Copyright (C) 2009-2013 Typesafe Inc. <http://www.typesafe.com>
 */
package play.api

import play.core.{ Router, SourceMapper }
import java.io.File

import scala.concurrent.Future

/**
 * Fake application as used by Play core tests.  This is needed since Play core can't depend on the Play test API.
 * It's also a lot simpler, doesn't load default config files etc.
 */
case class FakeApplication(config: Map[String, Any] = Map(),
                           path: File = new File("."),
                           sources: Option[SourceMapper] = None,
                           mode: Mode.Mode = Mode.Test,
                           global: GlobalSettings = DefaultGlobal,
                           plugins: Seq[Plugin] = Nil) extends Application {
  val classloader = Thread.currentThread.getContextClassLoader
  lazy val configuration = Configuration.from(config)
  def stop() = Future.successful(())
  val routes = Router.Null
}
