package ir.demisco.cfs.service.repository;

import ir.demisco.cloud.basic.model.entity.sec.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ApplicationUserRepository extends JpaRepository<User, Long> {
    @Query(value = " select t.id as application_user_id,t.user_name,t.nick_name  " +
            "     from sec.application_user t " +
            "     where not exists (select 1 " +
            "          from fnsc.financial_user iner_user " +
            "         where iner_user.application_user_id = t.id)  "
            , countQuery = " select" +
            "        count(*) " +
            "    from" +
            "        sec.application_user t      " +
            "    where" +
            "        not exists (" +
            "            select" +
            "                1           " +
            "            from" +
            "                fnsc.financial_user iner_user          " +
            "            where" +
            "                iner_user.application_user_id = t.id" +
            "        )  "
            , nativeQuery = true)
    Page<Object[]> getUserList(Pageable pageable);
}
