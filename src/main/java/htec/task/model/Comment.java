package htec.task.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table( name = "comments")
public class Comment {

    @Id
    @GeneratedValue(generator = "comment_seq_gen")
    @SequenceGenerator(name = "comment_seq_gen", sequenceName = "comment_seq_gen", initialValue = 1)
    private Long id;

    @Column(name = "content")
    private String content;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "modified_at")
    private Instant modifiedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private City city;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Comment(){
        this.createdAt = Instant.now();
        this.modifiedAt = Instant.now();
    }

    public Comment(String content){
        this.content = content;
        this.createdAt = Instant.now();
        this.modifiedAt = Instant.now();
    }

    public void update(String content){
        this.content = content;
        this.modifiedAt = Instant.now();
    }
}
