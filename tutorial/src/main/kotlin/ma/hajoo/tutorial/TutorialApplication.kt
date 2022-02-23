package ma.hajoo.tutorial

import ma.hajoo.tutorial.Property.BlogProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(BlogProperties::class)
class TutorialApplication

fun main(args: Array<String>) {
    runApplication<TutorialApplication>(*args)
}
