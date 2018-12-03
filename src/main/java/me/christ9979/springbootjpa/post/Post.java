package me.christ9979.springbootjpa.post;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Post {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    /*
        cascade :
            Cascade.PERSIST가 적용되어 있으므로 엔티티의 상태변화가 전파된다.
            즉 Post만 insert를 하더라도 comment도 자동으로 insert가 된다.
            기본값은 CascadeType이 정해져있지 않다.
            (정확히는 persistent context에서 상태변화)

        fetch :
            OneToMany는 기본적으로 FetchType.LAZY가 디폴트 값이다.
            왜냐하면, 많은 값을 사용하지도 않을건데, 미리 읽어오면 성능 효율이 떨어지기 때문이다.
     */
    @OneToMany(mappedBy = "post", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    private Set<Comment> comments = new HashSet<>();

    public void addComment(Comment comment) {
        this.getComments().add(comment);
        comment.setPost(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }
}