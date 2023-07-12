import { Component } from '@angular/core';
import {LabelService} from "../../service/label.service";

@Component({
  selector: 'app-quotes-input',
  templateUrl: './quotes-input.component.html',
  styleUrls: ['./quotes-input.component.scss']
})
export class QuotesInputComponent {

  constructor(private service:LabelService) {
  }

  words:string[] = [];

  html:string = ``;

  htmlData = {
    html: this.html
  };

  sendHtml(){
    this.service.sendHtml(this.htmlData).subscribe((resp) =>{
      this.html = resp;
      console.log(resp);
    },
        error => {
        console.log(error);
        }
    )
  }


  separateWords(word:String){
    // Remove espaços em branco antes e depois do texto
    word = word.trim();

    // Verifica se o texto está vazio
    if (word === '') {
      return [];
    }

    // Divide o texto em palavras com base na vírgula como separador
    var palavras = word.split(',');

    // Remove espaços em branco antes e depois de cada palavra
    palavras = palavras.map(function(palavra) {
      return palavra.trim();
    });

    return palavras;
  }
}
