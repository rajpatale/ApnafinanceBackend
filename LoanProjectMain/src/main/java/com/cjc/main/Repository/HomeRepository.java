package com.cjc.main.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cjc.main.model.EmployeeInfo;

@Repository
public interface HomeRepository  extends JpaRepository<EmployeeInfo, Integer> {

	List<EmployeeInfo> findAllByEname(String ename);
	EmployeeInfo findAllByUsernameAndPassword(String username,String password);

}
