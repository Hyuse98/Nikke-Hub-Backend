package com.hyuse.nikkeManager.repository

import com.hyuse.nikkeManager.enums.*
import com.hyuse.nikkeManager.model.Nikke
import jakarta.persistence.EntityManager
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles

//@Transactional
@DataJpaTest
@ActiveProfiles("test")
class NikkeRepositoryTest(
) {

    @Autowired
    private lateinit var nikkeRepository: NikkeRepository

    @Autowired
    private lateinit var entityManager: EntityManager

    @Test
    @DisplayName("Should find a nikke by name")
    fun findByNameSuccess() {

        val nikke = Nikke(
            id = null,
            name = "Test",
            core = 1,
            attraction = 1,
            skill1Level = 1,
            skill2Level = 1,
            burstLevel = 1,
            rarity = Rarity.SSR,
            ownedStatus = OwnedStatus.NOT_OWNED,
            burstType = BurstType.III,
            company = Company.PILGRIM,
            code = Code.ELECTRIC,
            weapon = Weapon.SR,
            nikkeClass = NikkeClass.ATTACKER,
            cube = null,
            doll = null
        )
        entityManager.persist(nikke)
        entityManager.flush()

        val name = "Test"
        val result = nikkeRepository.findByName(name)

        assertThat(result).isNotNull
        assertThat(result?.name).isEqualTo(name)
    }

    @Test
    @DisplayName("Should not find a nikke by name")
    fun findByNameFail() {

        nikkeRepository.deleteAll()

        val name = "Test"
        val result = nikkeRepository.findByName(name)

        assertThat(result).isNull()
    }

    @Test
    @DisplayName("Should delete a nikke by name")
    fun deleteByNameSuccess() {

        val nikke = Nikke(
            id = null,
            name = "Test",
            core = 1,
            attraction = 1,
            skill1Level = 1,
            skill2Level = 1,
            burstLevel = 1,
            rarity = Rarity.SSR,
            ownedStatus = OwnedStatus.NOT_OWNED,
            burstType = BurstType.III,
            company = Company.PILGRIM,
            code = Code.ELECTRIC,
            weapon = Weapon.SR,
            nikkeClass = NikkeClass.ATTACKER,
            cube = null,
            doll = null
        )
        entityManager.persist(nikke)
        entityManager.flush()

        val name = "Test"

        nikkeRepository.deleteByName(name)

        val result = nikkeRepository.findByName(name)
        assertThat(result).isNull()
    }
}
