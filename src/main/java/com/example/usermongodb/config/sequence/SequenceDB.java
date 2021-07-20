package com.example.usermongodb.config.sequence;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "db_sequences")
@Data
public class SequenceDB {
    @Id
    private String id;

    private long seq;
}
