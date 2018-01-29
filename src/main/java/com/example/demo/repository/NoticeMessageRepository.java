package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.demo.entity.NoticeMessage;

public interface NoticeMessageRepository extends JpaRepository<NoticeMessage, Integer>{

}
