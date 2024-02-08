package org.example.hexagonalexample.domain.core;

import java.io.Serializable;


/**
 * Base interface for Value Object
 * <pre>
 *     Value Objects implement this!
 * </pre>
 *
 * @author yongsu Seo
 */
public interface Vo<T> extends Serializable {

    boolean sameValueAs(T other);
}
