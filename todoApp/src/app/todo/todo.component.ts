import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { TodoService } from '../service/todo.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-todo',
  templateUrl: './todo.component.html',
  styleUrls: ['./todo.component.css']
})
export class TodoComponent implements OnInit {

  todoId: any;
  todoObject: any = {
    name: "",
    description: "",
    targetDate: new Date()
  };
  buttonName = "Add";

  constructor(
    private activatedRoute: ActivatedRoute,
    private todoService: TodoService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(data => {
      this.todoId = data['id'];

      if (this.todoId && this.todoId !== 'new') {
        this.todoService.getATodo(this.todoId).subscribe({
          next: (response: any) => {
            this.todoObject = response;
            this.buttonName = "Update";
          },
          error: (error: any) => {
            console.error('Error fetching todo:', error);
          }
        });
      } else {
        this.buttonName = "Add";
      }
    });
  }

  saveTodo(form: NgForm) {
    const username = sessionStorage.getItem('name') || 'default';

    if (this.todoId && this.todoId !== 'new') {
      this.todoService.updateTodo(this.todoId, this.todoObject).subscribe({
        next: (res) => {
          this.todoObject = res;
          this.router.navigate(['/todos', username]);
        },
        error: (err) => {
          console.error('Error updating todo:', err);
        }
      });
    } else {
      this.todoService.createTodo(this.todoObject).subscribe({
        next: (res) => {
          this.todoObject = res;
          this.router.navigate(['/todos', username]);
        },
        error: (err) => {
          console.error('Error creating todo:', err);
        }
      });
    }
  }

}
