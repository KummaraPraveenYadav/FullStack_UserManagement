import { HttpClient, HttpHandler, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

declare const appTodoUrl:string;
@Injectable({
  providedIn: 'root'
})
export class TodoService {

  constructor(private http:HttpClient) { }

 getAllTodos(): Observable<any>{
    // const params = new HttpParams();
    // params.set('userId', userId);

    return (this.http.get(appTodoUrl+"getAll"));
  }

  deleteTodo(todoId: BigInt): Observable<any>{
    return this.http.delete(appTodoUrl+'del/'+todoId);
  }

  updateTodo(todoId:BigInt,todo:any):Observable<any>{
return this.http.put(appTodoUrl+"update/"+todoId,todo
)
  }

  createTodo(todo:any){
    return this.http.post(appTodoUrl+'add',todo);
  }
  getATodo(userId:string): Observable<any>{
    const params = new HttpParams();
    params.set('userId', userId);

    return (this.http.get(appTodoUrl+"getAll/"+name, {params}));
  }
  
}