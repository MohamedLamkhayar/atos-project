package com.example.usermongodb.config.sequence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Objects;
import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
@Service
public class SequenceService {
    private MongoOperations mongoOperations;

    @Autowired
    public SequenceService(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    /**
     * generate a sequence for model's id
     * @param seqName
     * @return
     */
    public long generateSequence(String seqName) {
        SequenceDB counter = mongoOperations.findAndModify(query(where("_id").is(seqName)),
                new Update().inc("seq",1), options().returnNew(true).upsert(true),
                SequenceDB.class);
        return !Objects.isNull(counter) ? counter.getSeq() : 1;

    }
}
