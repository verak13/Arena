import { Output, SimpleChanges } from '@angular/core';
import { EventEmitter } from '@angular/core';
import { Input } from '@angular/core';
import { Component, OnChanges } from '@angular/core';

@Component({
  selector: 'app-pagination',
  templateUrl: './pagination.component.html',
  styleUrls: ['./pagination.component.scss']
})
export class PaginationComponent implements OnChanges {

  @Input() pageSize = 0;
  @Input() totalItems = 0;
  @Input() totalPages = 0;
  @Input() currentPage = 0;
  @Output() ChangePage = new EventEmitter<number>();

  constructor() {
  }
  

  ngOnChanges(changes: SimpleChanges): void {
    for (const propName in changes) {
      if (changes.hasOwnProperty(propName)) {
        let vary = this.get(propName);
        vary = changes[propName].currentValue;
      }
    }
  }

  get(element: string): number {
    switch (element) {
      case 'pageSize':
        return this.pageSize;
      case 'totalItems':
        return this.totalItems;
      case 'totalPages':
        return this.totalPages;
      default:
        return this.currentPage;
    }
  }

  public handlePage(e: any) {
    this.ChangePage.emit(e.pageIndex);
  }

}
