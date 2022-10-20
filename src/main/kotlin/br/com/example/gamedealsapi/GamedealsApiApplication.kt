package br.com.example.gamedealsapi

import br.com.example.gamedealsapi.gui.GameDeals
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.ConfigurableApplicationContext
import java.awt.EventQueue

@SpringBootApplication
@EnableFeignClients
class GamedealsApiApplication

fun main(args: Array<String>) {
    GameDeals.applyLookAndFeel()
    val context: ConfigurableApplicationContext = SpringApplicationBuilder(GamedealsApiApplication::class.java)
        .headless(false).run(*args)
    EventQueue.invokeLater {
        context.getBean(GameDeals::class.java)
    }
}

