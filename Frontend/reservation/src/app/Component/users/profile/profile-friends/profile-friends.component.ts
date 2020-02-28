import { Component, OnInit } from '@angular/core';
import {UserService} from '../../../../Service/user.service';
import {FriendsService} from '../../../../Service/friends.service';
import {any} from 'codelyzer/util/function';
import {ToastrManager} from 'ng6-toastr-notifications';

@Component({
  selector: 'app-profile-friends',
  templateUrl: './profile-friends.component.html',
  styleUrls: ['./profile-friends.component.css']
})
export class ProfileFriendsComponent implements OnInit {
  user: any;
  friends: any;         // lista objekata tipa User
  friendships: any;     // lista objekata Friends
  friendRequests: any;  // lista objekata Friends
  usersWhoAreNotFriends: any; // lista koja se popunjava nakon pretrage
  friendsSearch: string;
  userRole: any;
  constructor(private userService: UserService, private friendsService: FriendsService, private toastr: ToastrManager) {
    this.user = {username: '', firstname: '', lastName: ''};
    this.friendRequests = [];
    this.friends = [];
    this.friendships = [];
    this.friendsSearch = '';
  }

  ngOnInit() {
    this.userRole = this.userService.getLoggedUserType();
    const userTemp = JSON.parse(localStorage.getItem('loggedUser'));

    this.userService.getUserByUsername(userTemp.sub).subscribe( user => {
      this.user = user;
      this.friendsService.getFriendsByUser(this.user.username).subscribe(allFriendshipsList => {
        let list;
        list = allFriendshipsList;
        for (const f of list) {
          if (f.user2.username === this.user.username) {    // ako je trenutni user1, dodaj user2 u prijatelje ako je prihvaceno
            if (f.accepted === false) {
              this.friendRequests.push(f);                  // ako nije prihvatio dodaj prijateljstvo u listu da moze da prihvati
            } else {
              this.friends.push(f.user1);
              this.friendships.push(f);
            }
          } else if (f.accepted === true) {                 // ako je trenutni user1 dodaj user2 u prijatelje ako je prihvaceno
            this.friends.push(f.user2);
            this.friendships.push(f);
          }
        }
      });

    });
  }

  deleteFriendship(friend: any) {

    let friendshipIndex;
    let friendsIndex;
    for (let i = 0; i < this.friends.length; i++) {                   // uzme index gde se taj prijatelj sad nalazi u listi
      if (this.friends[i]['username'] === friend['username'] ||  // zbog brisanja iz nje
        this.friends[i]['username'] === friend['username']) {
        friendsIndex = i;
        break;
      }
    }
    for (let i = 0; i < this.friendships.length; i++) {               // uzima index friendship-a koje se brise
      if (this.friendships[i]['user1']['username'] === friend['username'] ||
        this.friendships[i]['user2']['username'] === friend['username']) {
        friendshipIndex = i;
        break;
      }
    }
    this.friendsService.deleteFriendship(this.friendships[friendshipIndex]['id']).subscribe( allFriendships => {
      this.toastr.infoToastr('Friend deleted successfully', 'Friend deleted');
      this.friendships.splice(friendshipIndex, 1);
      this.friends.splice(friendsIndex, 1);
    });
  }


  deleteRequest(friendship: any, index: any) {
    this.friendsService.deleteFriendship(friendship.id).subscribe( allFriendships => {
      this.friendRequests.splice(index, 1);
    });
  }

  acceptRequest(friendship: any, index: any) {
    friendship.accepted = true;
    this.friendsService.acceptFriendship(friendship.id).subscribe( acceptedFriendship => {
      this.friendRequests.splice(index, 1);                 // obrise ga iz request-ova jer je prihvacen
      if (this.user.username === acceptedFriendship['user1']['username']) {
        this.friends.push(acceptedFriendship['user2']);
      } else {
        this.friends.push(acceptedFriendship['user1']);
      }

      this.friendships.push(acceptedFriendship);
    });
  }

  search() {
    this.friendsService.search(this.friendsSearch, this.user.id).subscribe( notFriends => {
      this.usersWhoAreNotFriends = notFriends;
    });
  }

  addFriend(user: any, index: number) {
    const friendship = {user1: {}, user2: {}, accepted: false};
    friendship.user1 = this.user.id;
    friendship.user2 = user.id;
    this.friendsService.sendFriendRequest(friendship).subscribe( friends => {
      this.usersWhoAreNotFriends.splice(index, 1);
      this.toastr.infoToastr('Friend request sent successfully', 'Friend request sent');
    });
  }
}
