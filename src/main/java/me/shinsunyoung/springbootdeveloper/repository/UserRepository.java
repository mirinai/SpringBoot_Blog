package me.shinsunyoung.springbootdeveloper.repository;

import me.shinsunyoung.springbootdeveloper.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * User 엔티티의 데이터베이스 접근을 담당하는 JPA Repository 인터페이스.
 * Spring Data JPA를 사용하여 기본적인 CRUD 기능을 자동으로 제공함.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * 이메일을 기준으로 사용자 정보를 조회하는 메서드.
     * Spring Data JPA가 자동으로 쿼리를 생성하여 실행함.
     *
     * @param email 검색할 사용자의 이메일
     * @return Optional<User> - 해당 이메일을 가진 사용자가 존재하면 User 객체를 반환하고, 없으면 빈 Optional 반환
     */
    Optional<User> findByEmail(String email);
}

