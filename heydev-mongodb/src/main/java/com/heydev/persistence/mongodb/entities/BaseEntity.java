package com.heydev.persistence.mongodb.entities;

import java.io.Serializable;
import java.util.Date;
import org.bson.types.ObjectId;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Version;

public abstract class BaseEntity implements Serializable
{
    @Id private ObjectId id;
    private Date utcCreateDate;
    @Version private long version;  
    
    public ObjectId getId()
    {
        return this.id;
    }

    public void setId(ObjectId id)
    {
        this.id = id;
    }

    public Date getUtcCreateDate()
    {
        return this.utcCreateDate;
    }

    public void setUtcCreateDate(Date utcCreateDate)
    {
        this.utcCreateDate = utcCreateDate;
    }    

    public long getVersion()
    {
        return version;
    }

    public void setVersion(long version)
    {
        this.version = version;
    }

    @Override
    public boolean equals(Object obj)
    {
        boolean result = false;
        if (obj instanceof BaseEntity)
        {
            BaseEntity other = (BaseEntity)obj;
            if (this.id.equals(other.getId()))
            {
                result = true;
            }
        }
        
        return result;
    }

    @Override
    public String toString()
    {
        return this.id.toString();
    }
}

