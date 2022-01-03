package com.dichlowan.backend.repository;

import com.dichlowan.backend.model.UplinkModel;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public interface UplinkRepository extends MongoRepository<UplinkModel,String> {

    @Aggregation(pipeline = { "{ $sort: { receivedAt: 1} }"})
    public List<UplinkModel> findAll();

    @Aggregation(pipeline = { "{$match: {receivedAt: {$gte: {$date: \"?0\"},$lt: {$date: \"?1\"}}}}" })
    //@Query("{receivedAt: {$gte: {$date: \"?0\"},$lt: {$date: \"?1\"}}}")
    public List<UplinkModel> findBetween(Date after, Date before);

    public List<UplinkModel> findByDeviceId(String lastName);

    @Aggregation(pipeline = { "{$group: { _id: '', total: {$max: $receivedAt }}}" })
    public Date findLastDate();


}
