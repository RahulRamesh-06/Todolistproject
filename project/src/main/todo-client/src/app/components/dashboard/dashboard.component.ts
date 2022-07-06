import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { MasterService } from 'src/app/services/master.service';
import { SecurityService } from 'src/app/services/security/security.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {
  
  itemName: string;
  itemDescription: string;
  listItems: Array<any> = []
  isCheckedD:any;
  addedItems:Array<any> = []
  editedItems:Array<any> = []
  Router: any;
  
  constructor(
    private masterService: MasterService, 
    private securityService: SecurityService, 
    private toastrService: ToastrService
  ) { }
  
  ngOnInit() {
    // Fetch saved ToDo items against loggedin user
    this.addedItems=[];
    this.editedItems=[];
    this.masterService.getToDoList().subscribe((res) => {
      this.listItems = res;
      this.toastrService.success('List fetched successfully');
    });
  }
  
  /**
   * Check or uncheck ToDO items
   * @param itemIndex 
   */
  toggleStatus(itemIndex: number){
   
    this.listItems[itemIndex].isChecked = !this.listItems[itemIndex].isChecked
    this.isCheckedD=this.listItems[itemIndex].isChecked
    console.log(this.listItems[itemIndex].isChecked)
  }

  /**
   * Append item to ToDo list
   * @param itemName 
   * @param itemDescription 
   */
  addItem(itemName: string, itemDescription: string){
    this.addedItems.push({
      userId: this.securityService.details.userID,
      task:itemName,
      description: itemDescription,
      isChecked: false,
      lastUpdatedBy:'',
      lastUpdatedTime:''
    });
    this.itemName = null;
    this.itemDescription = null;
    this.toastrService.success('Task added successfully');
    this.masterService.saveToDoList(this.addedItems).subscribe((res) => {
      this.listItems = res;
      this.toastrService.success('List fetched successfully');
      this.ngOnInit();
    }, err => this.toastrService.error(err.message ? err.message : err));
  }

  /**
   * Remove ToDo item from the list
   * @param itemIndex 
   */
  // removeItem(itemIndex: any){
  //   // this.listItems.splice(itemIndex, 1);
  //   // const item = this.listItems.findIndex(itemIndex);
  //   console.log('itemid '+itemIndex)
  //   this.masterService.deleteFromList(itemIndex.taskId).subscribe((res) => {
  //     this.toastrService.success('List fetched successfully');
  //     this.ngOnInit();
  //   }, err => this.toastrService.error(err.message ? err.message : err));
    
  //   this.toastrService.warning('Task removed successfully');
  // }
  removeItem(itemIndex: number){
    // const item = this.listItems.findIndex(itemIndex);
    // this.listItems.splice(itemIndex, 1);
    // this.toastrService.warning('Task removed successfully');
    // console.log(itemIndex);
    this.masterService.deleteFromList(itemIndex).subscribe((res) => {
          this.ngOnInit();
        }, err => this.toastrService.error(err.message ? err.message : err));
  }

  /**
   * Save updated ToDo items list against logged-in user
   */
  saveList() {
    
    this.masterService.saveToDoList(this.addedItems).subscribe((res) => {
      this.listItems = res;
      this.toastrService.success('List fetched successfully');
      this.ngOnInit();
    }, err => this.toastrService.error(err.message ? err.message : err));
  }
  
  updateItemList(descriptionn:String,taskk:String,taskIdd:number) {
    console.log("any")
    console.log(descriptionn)
    console.log(taskk)
    console.log(taskIdd)
    console.log(this.isCheckedD)
    this.editedItems.push({
      userId: this.securityService.details.userID,
      isChecked: this.isCheckedD,
      taskId:taskIdd
    });
    console.log(this.editedItems)
    
    this.masterService.updateTodoList(this.editedItems,taskIdd).subscribe((res) =>  {
      this.listItems = res;
      this.toastrService.success('List fetched successfully');
      this.ngOnInit();
    })
  }

  /**
   * Log out from server
   */
  logout() {
    // this.ngOnInit();
    this.Router.navigateByUrl('home')
    // this.securityService.logout();
  }

  
}
