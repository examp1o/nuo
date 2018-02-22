/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { NuoTestModule } from '../../../test.module';
import { BulletinMySuffixDetailComponent } from '../../../../../../main/webapp/app/entities/bulletin-my-suffix/bulletin-my-suffix-detail.component';
import { BulletinMySuffixService } from '../../../../../../main/webapp/app/entities/bulletin-my-suffix/bulletin-my-suffix.service';
import { BulletinMySuffix } from '../../../../../../main/webapp/app/entities/bulletin-my-suffix/bulletin-my-suffix.model';

describe('Component Tests', () => {

    describe('BulletinMySuffix Management Detail Component', () => {
        let comp: BulletinMySuffixDetailComponent;
        let fixture: ComponentFixture<BulletinMySuffixDetailComponent>;
        let service: BulletinMySuffixService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [NuoTestModule],
                declarations: [BulletinMySuffixDetailComponent],
                providers: [
                    BulletinMySuffixService
                ]
            })
            .overrideTemplate(BulletinMySuffixDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(BulletinMySuffixDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BulletinMySuffixService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new BulletinMySuffix(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.bulletin).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
