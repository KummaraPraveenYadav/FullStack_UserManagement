import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { TodoService } from '../service/todo.service';

@Component({
  selector: 'app-list-todo',
  templateUrl: './list-todo.component.html',
  styleUrls: ['./list-todo.component.css']
})
export class ListTodoComponent implements OnInit{
 //http://localhost:4200/todo/Suman?id=edsdwofeehwfieohcefnsfhhhefajo
  todos:any;
  userId:string="";
  name: string = "";
  deleteMessage: string="";
  constructor(private activateRoute: ActivatedRoute, private todoService: TodoService,private route:Router ){}

  ngOnInit(): void {
    this.activateRoute.queryParams.subscribe(queryParams=>{
      this.userId = queryParams['id'];
    });
    this.activateRoute.params.subscribe(pathParams=>{
      this.name = pathParams['name'];
      console.log(this.name)
    });
    this.todoService.getAllTodos().subscribe({
      next: (successResponse)=>{
        
        console.log(successResponse); //for our confirmation
        this.todos = successResponse;
      },
      error: (error)=>{
        console.error(error);
      }
    });
  }

 deleteTodo(id: bigint): void {
  this.todoService.deleteTodo(id).subscribe({
    next: () => {
      // Correctly update the todos list
      this.todos = this.todos.filter((todo: any) => todo.id !== id);
      this.deleteMessage = "Todo has been deleted";
    },
    error: (error) => {
      console.error('Error deleting todo:', error);
    }
  });
}


 updateTodo(id: BigInt) : void{
  //todo update

  this.route.navigate(["/todo",id]);
 }

 addTodo():void{
  this.route.navigate(["/todo",'new']);
 }
}