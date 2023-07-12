import {Component, OnInit} from '@angular/core';
import {LabelModel} from "../../model/label-model";
import {AppComponent} from "../../app.component";
import {LabelService} from "../../service/label.service";

const labelsSelecionadas: number[] = [];

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.scss']
})

export class TableComponent implements OnInit {
  displayedColumns: string[] = ['date', 'key', 'value', 'actions'];

  constructor(private http:LabelService) {}
  labelList: LabelModel[] = [];

  hasLabelsSelect(): boolean {
    return labelsSelecionadas.length > 0;
  }
  ngOnInit(){
    this.findAll();
  }

  findAll(){
    this.http.findAll().subscribe((resp) =>{
        this.labelList = resp;
        console.log(this.labelList);
      },
      error => {
        console.log(error);
      })
  }
}
