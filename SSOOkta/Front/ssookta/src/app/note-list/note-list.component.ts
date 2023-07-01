import { Component } from '@angular/core';
import { NoteService } from '../note.service';
import { Note } from '../note';

@Component({
  selector: 'app-note-list',
  templateUrl: './note-list.component.html',
  styleUrls: ['./note-list.component.scss']
})
export class NoteListComponent {

  noteList : Note[];

  constructor(public noteService : NoteService){
    this.search();
  }

  search() {
    this.noteService.findAll().subscribe({
      next: v => this.noteList = v,
      error: e=> {
        console.log(e);
      }
    });
  }

  delete(item : Note){
    this.noteService.delete(item.id).subscribe();
    this.search();
  }
}
