import { Component } from '@angular/core';

@Component({
  selector: 'app-quotes-input',
  templateUrl: './quotes-input.component.html',
  styleUrls: ['./quotes-input.component.scss']
})
export class QuotesInputComponent {

  words:string[] = [];

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
