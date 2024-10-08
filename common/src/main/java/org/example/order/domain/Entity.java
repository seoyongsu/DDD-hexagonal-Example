package org.example.order.domain;

/**
 * Base interface for Entity Object
 * <pre>
 *     DomainEntity Objects implement this!
 * </pre>
 *
 * @author yongsu Seo
 */
public interface Entity<ID extends Identity> {

    /**
     * Entities Get identity
     */
    ID getId();


}
