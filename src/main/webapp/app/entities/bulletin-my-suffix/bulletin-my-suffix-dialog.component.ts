import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { BulletinMySuffix } from './bulletin-my-suffix.model';
import { BulletinMySuffixPopupService } from './bulletin-my-suffix-popup.service';
import { BulletinMySuffixService } from './bulletin-my-suffix.service';

@Component({
    selector: 'jhi-bulletin-my-suffix-dialog',
    templateUrl: './bulletin-my-suffix-dialog.component.html'
})
export class BulletinMySuffixDialogComponent implements OnInit {

    bulletin: BulletinMySuffix;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private bulletinService: BulletinMySuffixService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.bulletin.id !== undefined) {
            this.subscribeToSaveResponse(
                this.bulletinService.update(this.bulletin));
        } else {
            this.subscribeToSaveResponse(
                this.bulletinService.create(this.bulletin));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<BulletinMySuffix>>) {
        result.subscribe((res: HttpResponse<BulletinMySuffix>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: BulletinMySuffix) {
        this.eventManager.broadcast({ name: 'bulletinListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-bulletin-my-suffix-popup',
    template: ''
})
export class BulletinMySuffixPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private bulletinPopupService: BulletinMySuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.bulletinPopupService
                    .open(BulletinMySuffixDialogComponent as Component, params['id']);
            } else {
                this.bulletinPopupService
                    .open(BulletinMySuffixDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
