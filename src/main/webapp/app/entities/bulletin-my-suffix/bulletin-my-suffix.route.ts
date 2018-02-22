import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { BulletinMySuffixComponent } from './bulletin-my-suffix.component';
import { BulletinMySuffixDetailComponent } from './bulletin-my-suffix-detail.component';
import { BulletinMySuffixPopupComponent } from './bulletin-my-suffix-dialog.component';
import { BulletinMySuffixDeletePopupComponent } from './bulletin-my-suffix-delete-dialog.component';

@Injectable()
export class BulletinMySuffixResolvePagingParams implements Resolve<any> {

    constructor(private paginationUtil: JhiPaginationUtil) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const page = route.queryParams['page'] ? route.queryParams['page'] : '1';
        const sort = route.queryParams['sort'] ? route.queryParams['sort'] : 'id,asc';
        return {
            page: this.paginationUtil.parsePage(page),
            predicate: this.paginationUtil.parsePredicate(sort),
            ascending: this.paginationUtil.parseAscending(sort)
      };
    }
}

export const bulletinRoute: Routes = [
    {
        path: 'bulletin-my-suffix',
        component: BulletinMySuffixComponent,
        resolve: {
            'pagingParams': BulletinMySuffixResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'nuoApp.bulletin.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'bulletin-my-suffix/:id',
        component: BulletinMySuffixDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'nuoApp.bulletin.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const bulletinPopupRoute: Routes = [
    {
        path: 'bulletin-my-suffix-new',
        component: BulletinMySuffixPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'nuoApp.bulletin.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'bulletin-my-suffix/:id/edit',
        component: BulletinMySuffixPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'nuoApp.bulletin.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'bulletin-my-suffix/:id/delete',
        component: BulletinMySuffixDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'nuoApp.bulletin.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
