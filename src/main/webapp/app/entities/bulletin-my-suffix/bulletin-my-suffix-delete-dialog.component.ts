import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { BulletinMySuffix } from './bulletin-my-suffix.model';
import { BulletinMySuffixPopupService } from './bulletin-my-suffix-popup.service';
import { BulletinMySuffixService } from './bulletin-my-suffix.service';

@Component({
    selector: 'jhi-bulletin-my-suffix-delete-dialog',
    templateUrl: './bulletin-my-suffix-delete-dialog.component.html'
})
export class BulletinMySuffixDeleteDialogComponent {

    bulletin: BulletinMySuffix;

    constructor(
        private bulletinService: BulletinMySuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.bulletinService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'bulletinListModification',
                content: 'Deleted an bulletin'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-bulletin-my-suffix-delete-popup',
    template: ''
})
export class BulletinMySuffixDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private bulletinPopupService: BulletinMySuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.bulletinPopupService
                .open(BulletinMySuffixDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
