import {Task} from './Task';

export class Course {
  courseTitle: string;
  courseDescription: string;
  taskList: Task[];
  active: boolean;
}
