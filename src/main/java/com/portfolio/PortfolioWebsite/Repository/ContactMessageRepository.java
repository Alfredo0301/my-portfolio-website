package com.portfolio.PortfolioWebsite.Repository;

import com.portfolio.PortfolioWebsite.Model.ContactMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ContactMessageRepository extends JpaRepository<ContactMessage, Long> {
    List<ContactMessage> findByReadFalseOrderByCreatedAtDesc();
    List<ContactMessage> findAllByOrderByCreatedAtDesc();
}

