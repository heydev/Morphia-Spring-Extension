package com.heydev.persistence.mongodb.entities;

import java.io.Serializable;

public class BaseUniqueEmbeddedEntity<T> implements Serializable
{
    protected T key;

    @Override
    public boolean equals(Object obj)
    {
        boolean result = false;
        if (obj != null)
        {
            if (obj.getClass() == this.getClass())
            {
                if (((BaseUniqueEmbeddedEntity)obj).key.equals(this.key))
                {
                    result = true;
                }
            }
        }
        
        return result;
    }
}
