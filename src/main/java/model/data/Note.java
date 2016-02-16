package model.data;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by david on 7/2/16.
 */
@XmlRootElement
@XmlType( propOrder = { "id", "title", "text" } )
@Entity
@Table( name = "notes" )
@NamedQueries(value = {
        @NamedQuery(name = Note.RETRIEVE_ALL, query = "SELECT n FROM Note n"),
        @NamedQuery(name = Note.RETRIEVE_BY_ID, query = "SELECT n FROM Note n WHERE n.id = :id")
})
public class Note {

    public static final String RETRIEVE_ALL = "Retrieve all notes";
    public static final String RETRIEVE_BY_ID = "Retrieve note by id";

    @Id
    @GeneratedValue( strategy =  GenerationType.AUTO )
    @Column( name = "id" )
    private Long id;
    @Column( name = "title", nullable = false )
    private String title;
    @Column( name = "text" )
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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

    @Override
    public boolean equals( Object obj ) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Note other = (Note) obj;
        if( this.id == other.getId() )
            return true;
        return false;
    }
}
