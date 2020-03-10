package org.folio.modusers.domain;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

public class OffsetRequest implements Pageable {

  private int limit;
  private int offset;

  private Sort sort = Sort.by(Direction.DESC, "id");

  public OffsetRequest(int limit, int offset) {
    this.limit = limit;
    this.offset = offset;
  }

  @Override
  public int getPageNumber() {
    return offset / limit;
  }

  @Override
  public int getPageSize() {
    return limit;
  }

  @Override
  public long getOffset() {
    return offset;
  }

  @Override
  public Sort getSort() {
    return sort;
  }

  @Override
  public Pageable next() {
    return new OffsetRequest(getPageSize(), (int) (getOffset() + getPageSize()));
  }

  public Pageable previous() {
    return hasPrevious() ?
        new OffsetRequest(getPageSize(), (int) (getOffset() - getPageSize())) : this;
  }

  @Override
  public Pageable previousOrFirst() {
    return hasPrevious() ? previous() : first();
  }

  @Override
  public Pageable first() {
    return new OffsetRequest(getPageSize(), 0);
  }

  @Override
  public boolean hasPrevious() {
    return offset > limit;
  }
}
