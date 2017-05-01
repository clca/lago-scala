package com.example.hello.impl

import com.lightbend.lagom.scaladsl.api.ServiceCall
import com.lightbend.lagom.scaladsl.persistence.PersistentEntityRegistry
import com.example.hello.api.HellolagomService

/**
  * Implementation of the HellolagomService.
  */
class HellolagomServiceImpl(persistentEntityRegistry: PersistentEntityRegistry) extends HellolagomService {

  override def hello(id: String) = ServiceCall { _ =>
    // Look up the hello-lagom entity for the given ID.
    val ref = persistentEntityRegistry.refFor[HellolagomEntity](id)

    // Ask the entity the Hello command.
    ref.ask(Hello(id, None))
  }

  override def useGreeting(id: String) = ServiceCall { request =>
    // Look up the hello-lagom entity for the given ID.
    val ref = persistentEntityRegistry.refFor[HellolagomEntity](id)

    // Tell the entity to use the greeting message specified.
    ref.ask(UseGreetingMessage(request.message))
  }
}
