import {Component, OnInit} from '@angular/core';
import {LabelService} from "./service/label.service";
import {LabelModel} from "./model/label-model";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent{
  title = 'label_gen_ui';
}
