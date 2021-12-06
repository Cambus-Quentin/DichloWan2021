package com.dichlowan.backend.repository;

import com.dichlowan.backend.model.UplinkModel;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Component
public interface UplinkRepository extends MongoRepository<UplinkModel,String> {
    public List<UplinkModel> findAll();
    public List<UplinkModel> findByDeviceId(String lastName);

    @Aggregation(pipeline = { "{$group: { _id: '', total: {$max: $receivedAt }}}" })
    public Date findLastDate();
}
