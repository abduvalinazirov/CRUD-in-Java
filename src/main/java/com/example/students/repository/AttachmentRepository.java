package com.example.students.repository;

import com.example.students.entity.Attachment;
import com.example.students.entity.AttachmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, UUID> {
  Attachment findByHashId(String hashId);

  List<Attachment> findAllByAttchmentStatus(AttachmentStatus attachmentStatus);
}