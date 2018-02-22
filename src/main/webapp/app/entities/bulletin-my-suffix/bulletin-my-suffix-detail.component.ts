import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { BulletinMySuffix } from './bulletin-my-suffix.model';
import { BulletinMySuffixService } from './bulletin-my-suffix.service';

@Component({
    selector: 'jhi-bulletin-my-suffix-detail',
    templateUrl: './bulletin-my-suffix-detail.component.html'
})
export class BulletinMySuffixDetailComponent implements OnInit, OnDestroy {

    bulletin: BulletinMySuffix;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private bulletinService: BulletinMySuffixService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInBulletins();
    }

    load(id) {
        this.bulletinService.find(id)
            .subscribe((bulletinResponse: HttpResponse<BulletinMySuffix>) => {
                this.bulletin = bulletinResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInBulletins() {
        this.eventSubscriber = this.eventManager.subscribe(
            'bulletinListModification',
            (response) => this.load(this.bulletin.id)
        );
    }
}
