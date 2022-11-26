package com.example.BaiCK.repository;

import com.example.BaiCK.domain.FileImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileImage, Long> {
}
