package com.example

import akka.actor.{Actor, ActorLogging, Props}

import scala.concurrent.duration._

class PingActor extends Actor with ActorLogging {
  import PingActor._
  
  val pongActor = context.actorOf(PongActor.props, "pongActor")

  def receive = {
  	case Initialize => 
	    log.info("In PingActor - starting ping-pong")
  	  pongActor ! PingMessage("ping")	
  	case PongActor.PongMessage(text) =>
  	  log.info("In PingActor - received message: {}", text)
      import context.dispatcher
      context.system.scheduler.scheduleOnce(250.millis)(pongActor.tell(PingMessage("ping"), self))
  }
}

object PingActor {
  val props = Props[PingActor]
  case object Initialize
  case class PingMessage(text: String)
}