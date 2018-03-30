import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/Observable";

@Component({
  selector: 'app-component',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  title = 'expenses';

  expenses: Expense[]

  constructor(private http: HttpClient) {

  }

  ngOnInit() {
    this.http.get<Expense[]>('http://expenses.com:8081/api/v1/expenses', {
      headers: {
        // 'Access-Control-Request-Headers': '*',
        //'Access-Control-Allow-Origin': '*',
      }
    })
      .subscribe(data => this.expenses = data);
    console.log(this.expenses);
  }
}

class Expense {
  id: String
  title: String
  price: Price
  date: String
}

class Price {
  amount: Number;
  currency: string;
}
