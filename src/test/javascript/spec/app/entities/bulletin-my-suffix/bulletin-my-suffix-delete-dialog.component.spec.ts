/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { NuoTestModule } from '../../../test.module';
import { BulletinMySuffixDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/bulletin-my-suffix/bulletin-my-suffix-delete-dialog.component';
import { BulletinMySuffixService } from '../../../../../../main/webapp/app/entities/bulletin-my-suffix/bulletin-my-suffix.service';

describe('Component Tests', () => {

    describe('BulletinMySuffix Management Delete Component', () => {
        let comp: BulletinMySuffixDeleteDialogComponent;
        let fixture: ComponentFixture<BulletinMySuffixDeleteDialogComponent>;
        let service: BulletinMySuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [NuoTestModule],
                declarations: [BulletinMySuffixDeleteDialogComponent],
                providers: [
                    BulletinMySuffixService
                ]
            })
            .overrideTemplate(BulletinMySuffixDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(BulletinMySuffixDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BulletinMySuffixService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        spyOn(service, 'delete').and.returnValue(Observable.of({}));

                        // WHEN
                        comp.confirmDelete(123);
                        tick();

                        // THEN
                        expect(service.delete).toHaveBeenCalledWith(123);
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
