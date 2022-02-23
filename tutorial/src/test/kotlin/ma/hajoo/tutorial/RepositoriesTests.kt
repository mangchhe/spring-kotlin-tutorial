package ma.hajoo.tutorial

import ma.hajoo.tutorial.Entity.Article
import ma.hajoo.tutorial.Entity.User
import ma.hajoo.tutorial.Repository.ArticleRepository
import ma.hajoo.tutorial.Repository.UserRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.data.repository.findByIdOrNull
import org.junit.jupiter.api.Assertions.*
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RepositoriesTests @Autowired constructor(
    val entityManager: TestEntityManager,
    val userRepository: UserRepository,
    val articleRepository: ArticleRepository
){
    @Test
    fun `When findByIdOrNull then return Article`() {
        // given
        val juergen = User("springjuergen", "Jeurgen", "Hoeller")
        val article = Article("Spring Framework 5.0 goes GA", "Dear Spring community ...", "Lorem ipsum", juergen)
        entityManager.persist(juergen)
        entityManager.persist(article)
        entityManager.flush()
        // when
        val found = articleRepository.findByIdOrNull(article.id!!)
        // then
        assertEquals(found, article);
    }

    @Test
    fun `When findByLogin then return User`() {
        // given
        val juergen = User("springjuergen", "Jeurgen", "Hoeller")
        entityManager.persist(juergen)
        entityManager.flush()
        // when
        var user = userRepository.findByLogin(juergen.login)
        // then
        assertEquals(user, juergen);
    }
}