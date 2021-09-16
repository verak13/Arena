import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TableComponent } from './table/table.component';
import { MatTableModule } from "@angular/material/table";
import { MatButtonModule } from "@angular/material/button";
import { PaginationComponent } from './pagination/pagination.component';
import { MatPaginatorModule } from '@angular/material/paginator';
import { YesNoPipe } from './pipes/yes-no.pipe';



@NgModule({
  declarations: [
    TableComponent,
    PaginationComponent,
    YesNoPipe
  ],
  imports: [
    CommonModule,
    MatTableModule,
    MatButtonModule,
    MatPaginatorModule
  ],
  exports: [
    TableComponent,
    PaginationComponent
  ]
})
export class SharedModule { }
