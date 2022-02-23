package ma.hajoo.tutorial

import ma.hajoo.tutorial.Util.toSlug
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.http.HttpStatus

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class IntegrationTests(@Autowired val restTemplate: TestRestTemplate) {

    @BeforeAll
    fun setup() {
        println(">> Setup")
    }

    @Test
    fun `Assert blog page title, content and status code`() {
        val entity = restTemplate.getForEntity<String>("/")
        assertEquals(entity.statusCode, HttpStatus.OK);
        assertTrue(entity.body!!.contains("<h1>Blog</h1>"))
        assertTrue(entity.body!!.contains("Reactor"))
    }

    @Test
    fun `Assert article page title, content and status code`() {
        val title = "Reactor Aluminium has landed"
        val entity = restTemplate.getForEntity<String>("/article/${title.toSlug()}")
        assertEquals(entity.statusCode, HttpStatus.OK)
        assertTrue(entity.body!!.contains("title"))
        assertTrue(entity.body!!.contains("Lorem ipsum"))
        assertTrue(entity.body!!.contains("dolor sit amet"))
    }

    @AfterAll
    fun teardown() {
        println(">> Tear down")
    }

}