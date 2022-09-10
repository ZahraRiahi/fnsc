package ir.demisco.cfs.service.repository;

import ir.demisco.cfs.model.entity.FinancialUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FinancialUserRepository extends JpaRepository<FinancialUser, Long> {


    @Query("select fu.applicationUser.id,fu.id,u.username,u.nickName from FinancialUser fu join User u on fu.applicationUser.id=u.id ")
    List<Object[]> getFinancialUserList();


}
