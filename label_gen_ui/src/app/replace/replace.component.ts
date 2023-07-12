import { Component } from '@angular/core';
import {LabelService} from "../service/label.service";
import {Html} from "../model/html";

@Component({
  selector: 'app-replace',
  templateUrl: './replace.component.html',
  styleUrls: ['./replace.component.scss']
})
export class ReplaceComponent {

  constructor(private service: LabelService) {
  }


  words: string[] = [];

  html: Html = {
    html: ""
  }

  result: string = "";

  sendHtml() {
    this.service.sendHtml(this.html).subscribe((resp) => {
          console.log(resp);
          this.result = resp.html;
        },
        error => {
          console.error(error);
        }
    )
  }

}
