package org.example.vocabulary.entity.resource;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "resource")
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="original_name")
    private String originalName;

    private String key;
    private Long size;
    @Column(name = "content_type")
    private String contentType;


    @Enumerated(EnumType.STRING)
    private ResourceStatus status;

    @Column(name= "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;
    @Column(name="passive_at")
    private LocalDateTime passiveAt;

}