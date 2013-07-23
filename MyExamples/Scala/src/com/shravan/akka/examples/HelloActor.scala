package com.shravan.akka.examples

//import akka.actor.Actor
//import akka.actor.ActorSystem
//import akka.actor.Props
import scala.actors.Actor

class HelloActor extends /*Actor */ {
  def receive = {
    case "hello" => println("hello back at you")
    case _       => println("huh?")
  }
}